package com.example.projectsem4.controller;

import com.example.projectsem4.config.PaypalPaymentIntent;
import com.example.projectsem4.config.PaypalPaymentMethod;
import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.entity.Bill;
import com.example.projectsem4.entity.Place;
import com.example.projectsem4.repository.AppointmentRepository;
import com.example.projectsem4.repository.BillRepository;
import com.example.projectsem4.repository.VaccineRepository;
import com.example.projectsem4.service.PaypalService;
import com.example.projectsem4.utils.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentController {

    @Autowired
    private BillRepository billRepository;

    public int payid;

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private VaccineRepository vaccineRepository;

    @GetMapping("/payment/{id}")
    public String index(Model model, @PathVariable int id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        model.addAttribute("appointment", appointment.get());
        return "payment";
    }

    @PostMapping("/pay")
    public String pay(HttpServletRequest request, @RequestParam("price") double price, @RequestParam("paymentId") int id ){
        String cancelUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
        String successUrl = Utils.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
        this.payid = id;
        try {
            Payment payment = paypalService.createPayment(
                    price,
                    "USD",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "payment description",
                    cancelUrl,
                    successUrl);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return "redirect:" +links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping(URL_PAYPAL_CANCEL)
    public String cancelPay(){
        return "cancel";
    }

    @GetMapping(URL_PAYPAL_SUCCESS)
    public String successPay(Model model,@RequestParam("paymentId") String paymentId,
                             @RequestParam("PayerID") String payerId
                             ){
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                model.addAttribute("payment", payment);
                Optional<Appointment> appointment = appointmentRepository.findById(this.payid);
                model.addAttribute("appointment", appointment.get());
                Bill bill = new Bill();
                bill.setAppointment(appointment.get());
                bill.setAppointment_id(appointment.get().getAppointment_id());
                bill.setAddress(appointment.get().getAddress());
                bill.setGender(appointment.get().getGender());
                bill.setDate_of_birth(appointment.get().getDate_of_birth());
                bill.setPrice((float) appointment.get().getVaccine().getPrice());
                bill.setPhone(appointment.get().getPhone());
                bill.setDate(appointment.get().getDate());
                bill.setVaccine_id(appointment.get().getVaccine_id());
                bill = billRepository.saveAndFlush(bill);
                model.addAttribute("bill", bill);
                return "patient_registration";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }
    @PostMapping("/pay/successbill")
    public String paySuccess(Model model, @RequestParam String name, String birthday, String gender){
        model.addAttribute("name", name);
        model.addAttribute("birthday", birthday);
        model.addAttribute("gender", gender);
        return "success";
    }

}


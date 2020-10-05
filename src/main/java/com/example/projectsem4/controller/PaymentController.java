package com.example.projectsem4.controller;

import com.example.projectsem4.config.PaypalPaymentIntent;
import com.example.projectsem4.config.PaypalPaymentMethod;
import com.example.projectsem4.entity.Appointment;
import com.example.projectsem4.entity.Place;
import com.example.projectsem4.repository.AppointmentRepository;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class PaymentController {

    public int payid;

    public static final String URL_PAYPAL_SUCCESS = "pay/success";
    public static final String URL_PAYPAL_CANCEL = "pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    private AppointmentRepository appointmentRepository;

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
                return "patient_registration";
            }
        } catch (PayPalRESTException e) {
            log.error(e.getMessage());
        }
        return "redirect:/";
    }

}


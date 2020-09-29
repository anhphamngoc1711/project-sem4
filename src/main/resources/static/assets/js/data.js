function login(){
    var username = document.getElementById('Username').value;
    var password = document.getElementById('Password').value;
if(username==""){
    document.getElementById('Username-error').innerHTML = ('Vui lòng nhập tên tài khoản');
}else{
    document.getElementById('Username-error').innerHTML =('');
}
if(password==""){
    document.getElementById('Password-error').innerHTML = ('Vui lòng nhập mật khẩu');
}else{
    document.getElementById('Password-error').innerHTML =('');
}
}
function signup(){
    var username = document.getElementById('Username').value;
    var password = document.getElementById('Password').value;
    var repassword = document.getElementById('Repassword').value;
    if(username==""){
        document.getElementById('Username-error').innerHTML = ('Vui lòng nhập tên tài khoản');
    }else{
        document.getElementById('Username-error').innerHTML =('');
    }
    if(password==""){
        document.getElementById('Password-error').innerHTML = ('Vui lòng nhập mật khẩu');
    
    }else{
        document.getElementById('Password-error').innerHTML =('');
    
    }
    // if(repassword==""){
    //     document.getElementById('Repassword-error').innerHTML = ('Enter a Password');
    // }else{
    //     document.getElementById('Repassword-error').innerHTML =('');
    // }
    if(password!=repassword){
        document.getElementById('Repassword-error').innerHTML = ('Mật khẩu không khớp. Thử lại.');
    }else{
        document.getElementById('Repassword-error').innerHTML =('');
    }
}

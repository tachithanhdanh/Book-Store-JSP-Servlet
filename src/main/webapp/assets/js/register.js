const password = document.querySelector('#password');
const passwordConfirm = document.querySelector('#passwordConfirm');
const errorPassword = document.querySelector('#errorPassword');
const errorPasswordConfirm = document.querySelector('#errorPasswordConfirm');
const username = document.querySelector('#username');
const errorUsername = document.querySelector('#errorUsername');
const email = document.querySelector('#email');
const errorEmail = document.querySelector('#errorEmail');
const usernameHelp = document.querySelector('#usernameHelp');
const usernameRegex = /^[a-z][a-z0-9]{5,29}$/;
const emailRegex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})*$/;
const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
const passwordHelp = document.querySelector('#passwordHelp');
const submitButton = document.querySelector('#submit');
const acceptTermCondition = document.querySelector('#acceptTermCondition');

passwordConfirm.addEventListener('keyup', (e) => {
    if (password.value !== e.target.value) {
        errorPasswordConfirm.innerHTML="Password does not match!";
    } else {
        errorPasswordConfirm.innerHTML="";
    }
});

password.addEventListener('keyup', (e) => {
    if (passwordConfirm.value !== e.target.value) {
        errorPasswordConfirm.innerHTML="Password does not match!";
    } else {
        errorPasswordConfirm.innerHTML="";
    }
});

password.addEventListener('keyup', (e) => {
    if (!passwordRegex.test(e.target.value)) {
        passwordHelp.style.display = "block";
    } else {
        passwordHelp.style.display = "none";
    }
});

password.addEventListener('keyup', (e) => {
    console.log(e.target.value);
    errorPassword.innerHTML = "";
});

username.addEventListener('keyup', (e) => {
    if (e.target.value) {
        errorUsername.innerHTML = "";
    }
});

username.addEventListener('keyup', (e) => {
    if (!usernameRegex.test(e.target.value)) {
        usernameHelp.style.display = "block";
    } else {
        usernameHelp.style.display = "none";
    }
});

email.addEventListener('focusout', (e) => {
    if (e.target.value && !emailRegex.test(e.target.value)) {
        errorEmail.innerHTML="Invalid email address!";
    } else {
        errorEmail.innerHTML="";
    }
});

submitButton.addEventListener('click', (e) => {
    if (usernameHelp.style.display === "block"
        || passwordHelp.style.display === "block"
        || errorEmail.innerHTML
        || errorPasswordConfirm.innerHTML
        || errorUsername.innerHTML) {
        e.preventDefault();
    }
    if (!username.value) {
        errorUsername.innerHTML="Username is required!";
        e.preventDefault();
    }
    if (!password.value) {
        errorPasswordConfirm.innerHTML="Password is required!";
        e.preventDefault();
    }
});

acceptTermCondition.addEventListener('click', (e) => {
    submitButton.disabled = !e.target.checked;
});

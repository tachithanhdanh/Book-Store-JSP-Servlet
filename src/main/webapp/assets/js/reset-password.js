const emailRegex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})*$/;
const email = document.querySelector('#email');
const errorEmail = document.querySelector('#errorEmail');
const submitButton = document.querySelector('#submit');
const username = document.querySelector('#username');
const errorUsername = document.querySelector('#errorUsername');

submitButton.addEventListener('click', (e) => {
    if (!emailRegex.test(email.value)) {
        errorEmail.innerHTML="Invalid email address!";
        e.preventDefault();
    }

    if (!username.value) {
        errorUsername.innerHTML="Username cannot be empty!";
        e.preventDefault();
    }
});

email.addEventListener('keyup', (e) => {
    if (emailRegex.test(e.target.value)) {
        errorEmail.innerHTML = "";
    }
});

username.addEventListener('keyup', (e) => {
    if (e.target.value) {
        errorUsername.innerHTML = "";
    }
});
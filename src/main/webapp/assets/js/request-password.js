const emailRegex = /^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})*$/;
const email = document.querySelector('#email');
const errorEmail = document.querySelector('#errorEmail');
const submitButton = document.querySelector('#submit');
const username = document.querySelector('#username');
const errorUsername = document.querySelector('#errorUsername');
const formFloatingList = document.getElementsByClassName('form-floating');

submitButton.addEventListener('click', (e) => {
    if (!emailRegex.test(email.value)) {
        errorEmail.innerHTML="Invalid email address!";
        formFloatingList[0].classList.add('is-invalid');
        email.classList.add('is-invalid');
        e.preventDefault();
    }

    if (!username.value) {
        errorUsername.innerHTML="Username cannot be empty!";
        formFloatingList[1].classList.add('is-invalid');
        username.classList.add('is-invalid');
        e.preventDefault();
    }
});

email.addEventListener('keyup', (e) => {
    if (emailRegex.test(e.target.value)) {
        formFloatingList[0].classList.remove('is-invalid');
        email.classList.remove('is-invalid');
        // errorEmail.innerHTML = "";
    } else {
        formFloatingList[0].classList.add('is-invalid');
        email.classList.add('is-invalid');
    }
});

username.addEventListener('keyup', (e) => {
    if (e.target.value) {
        // errorUsername.innerHTML = "";
        formFloatingList[1].classList.remove('is-invalid');
        username.classList.remove('is-invalid');
    } else {
        formFloatingList[1].classList.add('is-invalid');
        username.classList.add('is-invalid');
    }
});
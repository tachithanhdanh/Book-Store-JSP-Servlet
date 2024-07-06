const currentPassword = document.getElementById('currentPassword');
const newPassword = document.getElementById('newPassword');
const newPasswordRetype = document.getElementById('newPasswordRetype');
const errorCurrentPassword = document.getElementById('errorCurrentPassword');
const errorNewPassword = document.getElementById('errorNewPassword');
const errorNewPasswordRetype = document.getElementById('errorNewPasswordRetype');
const changePasswordBtn = document.getElementById('changePasswordBtn');
const formFloatingList = document.getElementsByClassName('form-floating');

let isPasswordMatch = false;
let isPasswordSecure = false;
let isPasswordEmpty = true;

const passwordElements = [currentPassword, newPassword, newPasswordRetype];
const newPasswordElements = [newPassword, newPasswordRetype];

const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

newPasswordElements.forEach((element) => {
    element.addEventListener('keyup', () => {
        if (newPasswordRetype.value && newPassword.value !== newPasswordRetype.value) {
            errorNewPasswordRetype.innerHTML = "Password does not match! Enter new password again here.";
            formFloatingList[2].classList.add('is-invalid');
            newPasswordRetype.classList.add('is-invalid');
            isPasswordMatch = false;
        } else {
            errorNewPasswordRetype.innerHTML = "";
            formFloatingList[2].classList.remove('is-invalid');
            newPasswordRetype.classList.remove('is-invalid');
            isPasswordMatch = true;
        }
    });
});

newPassword.addEventListener('keyup', () => {
    if (newPassword.value && !passwordRegex.test(newPassword.value)) {
        errorNewPassword.innerHTML = "Choose a more secure password that you don't use anywhere else. It should be at least 8 characters and difficult for others to guess.";
        formFloatingList[1].classList.add('is-invalid');
        newPassword.classList.add('is-invalid');
        isPasswordSecure = false;
    } else {
        errorNewPassword.innerHTML = "";
        formFloatingList[1].classList.remove('is-invalid');
        newPassword.classList.remove('is-invalid');
        isPasswordSecure = true;
    }
});

function checkEmptyPassword() {
    let isEmpty = false;
    passwordElements.forEach((element) => {
        if (!element.value) {
            isEmpty = true;
        }
    });
    return isEmpty;
}

passwordElements.forEach((element) => {
    element.addEventListener('keyup', () => {
        isPasswordEmpty = checkEmptyPassword();
        changePasswordBtn.disabled = isPasswordEmpty || !isPasswordMatch || !isPasswordSecure;
    });
});
const newPassword = document.getElementById('newPassword');
const newPasswordRetype = document.getElementById('newPasswordRetype');
const errorNewPassword = document.getElementById('errorNewPassword');
const errorNewPasswordRetype = document.getElementById('errorNewPasswordRetype');
const changePasswordBtn = document.getElementById('changePasswordBtn');
const formFloatingList = document.getElementsByClassName('form-floating');

const passwordRegex = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;

// Check if password is valid
let isPasswordMatch = newPassword && newPassword.value === newPasswordRetype.value;
let isPasswordSecure = newPassword && passwordRegex.test(newPassword.value);
let isPasswordEmpty = true;

// Elements to check for errors
const newPasswordElements = [newPassword, newPasswordRetype];
const errorElements = [errorNewPassword, errorNewPasswordRetype];

// Check if there is any error message
// If there is, add is-invalid class to the form-floating and password elements
for (let i = 0; i < errorElements.length; i++) {
    if (errorElements[i].innerHTML) {
        formFloatingList[i].classList.add('is-invalid');
        newPasswordElements[i].classList.add('is-invalid');
    }
}

// if password does not match, add is-invalid class to the form-floating and password elements
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

// if password is not secure, add is-invalid class to the form-floating and password elements
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

// Check if password is empty
function checkEmptyPassword() {
    let isEmpty = false;
    newPasswordElements.forEach((element) => {
        if (!element.value) {
            isEmpty = true;
        }
    });
    return isEmpty;
}

// Disable change password button if password is empty, does not match, or not secure
newPasswordElements.forEach((element) => {
    element.addEventListener('keyup', () => {
        isPasswordEmpty = checkEmptyPassword();
        changePasswordBtn.disabled = isPasswordEmpty || !isPasswordMatch || !isPasswordSecure;
    });
});
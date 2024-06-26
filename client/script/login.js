document.getElementById("myForm").addEventListener("submit", function (event) {
  event.preventDefault();

  var formData = new FormData(this);
  var jsonData = {};

  formData.forEach(function (value, key) {
    jsonData[key] = value;
  });

  fetch("http://localhost:8081/auth/sign-in", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(jsonData),
  })
    .then((response) => response.text())
    .then((token) => {
      if (token == "Неправильное имя пользователя или пароль") {
        alert(token);
      } else {
        localStorage.setItem("token", token);
        window.location.href = "../writePage.html";
      }
    });
});

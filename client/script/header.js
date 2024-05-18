document.addEventListener("DOMContentLoaded", function () {
    let token = localStorage.getItem("token");

    if (!token) {
        let hiddenElementLogout = document.querySelector(".logout");
        let hiddenElementName = document.querySelector(".name");
        let hiddenElementWrite = document.querySelector(".write");
        if (hiddenElementWrite) hiddenElementWrite.style.display = "none";
        if (hiddenElementLogout) hiddenElementLogout.style.display = "none";
        if (hiddenElementName) hiddenElementName.style.display = "none";
    } else {
        let hiddenElementLogin = document.querySelector(".login");
        let hiddenElementReg = document.querySelector(".registration");
        if (hiddenElementLogin) hiddenElementLogin.style.display = "none";
        if (hiddenElementReg) hiddenElementReg.style.display = "none";
        let token = localStorage.getItem("token");

        let headers = new Headers();
        headers.append("Authorization", "Bearer " + token);
        fetch("http://localhost:8081/secured/user/fio", {
            method: "GET",
            headers: headers,
        })
            .then((response) => {
                return response.text();
            })
            .then((data) => {
                document.querySelector(".name").innerHTML = data;
            });
    }
    document.querySelector(".logout").addEventListener("click", function () {
        localStorage.clear();
        location.reload();
    });
});

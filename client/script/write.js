fetch("http://localhost:8081/map/read", {
  method: "GET",
  headers: {
    "Content-Type": "application/json",
  },
})
  .then((response) => response.json())
  .then((json) => {
    let mapsData = json;
    let select = document.querySelector(".list__maps");

    mapsData.forEach(function (map) {
      let option = document.createElement("option");
      option.text = map.mapname + " - " + map.date;
      option.value = map.id;
      select.appendChild(option);
    });
  });

document
  .getElementById("MapNameForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
    let select = document.querySelector(".list__maps");
    var selectedOption = select.options[select.selectedIndex];
    var optionValue = selectedOption.value;
    fetch(`http://localhost:8081/secured/map/add/${optionValue}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    }).then(() => {
      window.location.href = "../profile.html";
    });
  });

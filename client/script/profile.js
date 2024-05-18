let select = document.querySelector(".myMaps");

const token = localStorage.getItem("token");
if (!token) {
  console.error("Token not found in localStorage");
} else {
  fetch("http://localhost:8081/secured/maps", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => response.json())
    .then((json) => {
      console.log("");
      json.forEach((map) => {
        let option = document.createElement("option");
        option.textContent = `${map.mapname} - ${map.date}`;

        let button = document.createElement("button");
        button.textContent = "Отменить запись";
        button.addEventListener("click", () => {
          sendPostRequest(map.id);
        });

        let div = document.createElement("div");
        div.appendChild(option);
        div.appendChild(button);

        select.appendChild(div);
      });
    });
}

function sendPostRequest(mapId) {
  const token = localStorage.getItem("token");

  fetch(`http://localhost:8081/secured/map/remove/${mapId}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  }).then(() => location.reload());
}

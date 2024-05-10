document.getElementById("adminPost").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let formData = new FormData(document.getElementById("adminPost"));
    fetch("/admin", {
        method: "POST",
        body: formData
    }).then(response => {
        // Обработка ответа, если необходимо
        console.log("POST запрос выполнен успешно");
    }).catch(error => {
        console.error('Ошибка при отправке запроса:', error);
    });
        event.target.reset();
});

document.getElementById("adminDelete").addEventListener("submit", function(event) {
    event.preventDefault(); // Предотвращаем стандартное поведение формы
    let delData = new FormData(document.getElementById("adminDelete"));
    fetch("/admin", {
        method: "DELETE",
        body: delData
    }).then(response => {
        // Обработка ответа, если необходимо
        console.log("DELETE запрос выполнен успешно");
    }).catch(error => {
        console.error('Ошибка при отправке запроса:', error);
    });
        event.target.reset();
});
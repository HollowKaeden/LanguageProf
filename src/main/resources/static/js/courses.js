[].forEach.call(document.querySelectorAll("#signup_form"), function (form)
{
    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Предотвращаем стандартное поведение формы
        let formData = new FormData(form);
        fetch("/courses", {
            method: "POST",
            body: formData
        }).then(response => {
            // Обработка ответа, если необходимо
            console.log("POST запрос выполнен успешно");
            location.reload();
        }).catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
            event.target.reset();
    });
});

[].forEach.call(document.querySelectorAll("#delete_form"), function (form)
{
    form.addEventListener("submit", function(event) {
        event.preventDefault(); // Предотвращаем стандартное поведение формы
        let formData = new FormData(form);
        fetch("/courses", {
            method: "DELETE",
            body: formData
        }).then(response => {
            // Обработка ответа, если необходимо
            console.log("POST запрос выполнен успешно");
            location.reload();
        }).catch(error => {
            console.error('Ошибка при отправке запроса:', error);
        });
            event.target.reset();
    });
});
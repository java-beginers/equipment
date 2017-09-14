// Выводит на экран окно подтверждения удаления записи
function confirmDelete(itemName, deleteLink) {
    if (true == confirm('Действительно удалить запись "' + itemName + '"?')) {
        window.location.href = deleteLink;
    }
}

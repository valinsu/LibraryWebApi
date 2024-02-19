// Определение функции deleteBook в глобальной области видимости
function deleteBook(isbn) {
    // Отправка DELETE запроса на удаление книги
    fetch(`/api/books/delete_book/${isbn}`, {
        method: 'DELETE'
    })
        .then(() => {
            // Удаляем книгу из списка на клиенте
            const bookItem = document.querySelector(`li strong:contains("ISBN: ${isbn}")`).parentNode;
            bookItem.parentNode.removeChild(bookItem);
        })
        .catch(error => console.error('Error:', error));
}

// Определение функции editBook в глобальной области видимости
function editBook(isbn) {
    // Здесь можно реализовать логику для открытия формы редактирования существующей книги
    console.log(`Editing book with ISBN ${isbn}`);
}

// Ждем, когда загрузится весь контент страницы
document.addEventListener('DOMContentLoaded', function() {
    const addBookForm = document.getElementById('addBook');
    const bookList = document.getElementById('books');

    // Функция для добавления новой книги в список
    function addBookToList(book) {
        const li = document.createElement('li');
        li.innerHTML = `
            <strong>Title:</strong> ${book.title}<br>
            <strong>Author:</strong> ${book.author}<br>
            <strong>ISBN:</strong> ${book.isbn}<br>
            <strong>Genre:</strong> ${book.genre}<br>
            <strong>Description:</strong> ${book.description}
            <button onclick="deleteBook('${book.isbn}')">Delete</button>
            <button onclick="editBook('${book.isbn}')">Edit</button>
        `;
        bookList.appendChild(li);
    }

    // Обработчик отправки формы добавления книги
    addBookForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(addBookForm);
        const book = {};
        formData.forEach((value, key) => {
            book[key] = value;
        });

        // Отправка POST запроса на создание книги
        fetch('/api/books/save_book', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(book)
        })
            .then(response => response.json())
            .then(data => {
                addBookToList(data);
                addBookForm.reset();
            })
            .catch(error => console.error('Error:', error));
    });

    // Загрузка списка книг при загрузке страницы
    fetch('/api/books')
        .then(response => response.json())
        .then(books => {
            books.forEach(book => addBookToList(book));
        })
        .catch(error => console.error('Error:', error));
});

import axios from '../custom-axios/axios';

const BookRepository = {
    fetchBooks: () => {
        return axios.get("/api/book");
    },
    fetchAuthors: () => {
        return axios.get("/api/author");
    },
    addBook: (name, category, author, availableCopies) => {
        return axios.post("/api/book", {
            "name" : name,
            "category" : category,
            "authorId" : author,
            "availableCopies" : availableCopies,
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/api/book/edit/${id}`, {
            "name" : name,
            "category" : category,
            "authorId" : author,
            "availableCopies" : availableCopies,
        });
    },
    deleteBook: (id) => {
        return axios.delete(`/api/book/delete/${id}`);
    },
    getBookById: (id) => {
        return axios.get(`/api/book/${id}`)
    },
    markAsTaken: (id) => {
        return axios.post(`/api/book/markAsTaken/${id}`)
    }

};

export default BookRepository;
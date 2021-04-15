import './App.css';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import React, {Component} from "react";
import BooksList from "./components/BookList";
import Header from "./components/Header";
import BookRepository from "./repo/BookRepository";
import BookAdd from "./components/BookAdd";
import BookEdit from "./components/BookEdit";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Route path={"/book/add"} exact render={() =>
                            <BookAdd authors={this.state.authors}
                                     onAddBook={this.addBook}/>}
                        />
                        <Route path={"/book/edit/:id"} exact render={() =>
                            <BookEdit authors={this.state.authors}
                                      onEditBook={this.editBook}
                                      book={this.state.selectedBook}/>}/>


                        <Route path={"/books"} exact render={() =>
                            <BooksList books={this.state.books}
                                       onDelete={this.deleteBook}
                                       onEdit={this.getBook}
                                       onMarkAsTaken={this.markAsTaken}/>}
                        />
                        <Redirect to={"/books"}/>
                    </div>
                </main>
            </Router>
        );
    }

    componentDidMount() {
        this.loadBooks();
        this.loadAuthors();
    }

    loadBooks = () => {
        BookRepository.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadAuthors = () => {
        BookRepository.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }

    addBook = (name, category, authorId, availableCopies) => {
        BookRepository.addBook(name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    deleteBook = (id) => {
        BookRepository.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    editBook = (id, name, category, authorId, availableCopies) => {
        BookRepository.editBook(id, name, category, authorId, availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        BookRepository.getBookById(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    markAsTaken = (id) => {
        BookRepository.markAsTaken(id)
            .then(() => {
                this.loadBooks();
            });
    }



}

export default App;
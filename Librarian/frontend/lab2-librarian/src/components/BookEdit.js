import React from 'react';
import {useHistory} from 'react-router-dom';

const BookEdit = (props) => {



    const onFormSubmit = (e) => {
        e.preventDefault();
        const name = formData.name !== "" ? formData.name : props.book.name;
        const category = formData.category !== "" ? formData.category : props.book.category;
        const authorId = formData.author !== 0 ? formData.author: props.book.author;
        const availableCopies = parseInt(formData.availableCopies) !== 0 ? parseInt(formData.availableCopies) : parseInt(props.product.availableCopies);

        props.onEditBook(props.book.id, name, category, authorId, availableCopies);
        history.push("/books");
    }
    const handleChange = (e) => {
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }
    const history = useHistory();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: "",
        author: 1,
        availableCopies: 1
    })
    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Book name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder={props.book.name}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Book Category</label>
                        <input type="text"
                               className="form-control"
                               id="category"
                               name="category"
                               required
                               placeholder={props.book.category}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Available Copies</label>
                        <input type="text"
                               className="form-control"
                               id="availableCopies"
                               name="availableCopies"
                               required
                               placeholder={props.book.availableCopies}
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Author</label>
                        <select name="author" className="form-control" onChange={handleChange}>
                            {props.authors.map((term) => {
                                if(props.book.author !== undefined &&
                                    props.book.author.id === term.id)
                                    return <option selected={props.book.author.id} value={term.id}>{term.name}</option>
                                else return <option value={term.id}>{term.name}</option>
                            })}

                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    )



}

export default BookEdit;
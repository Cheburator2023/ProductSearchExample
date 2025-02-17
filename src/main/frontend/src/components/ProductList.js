import React, { useState, useEffect } from 'react';

const ProductList = () => {
    const [products, setProducts] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        fetchProducts();
    }, []);

    const fetchProducts = (query = '') => {
        fetch(`http://localhost:8080/products/search?searchTerm=${query}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => setProducts(data))
            .catch(error => console.error('Error fetching products:', error));
    };

    const handleSearchChange = (event) => {
        const value = event.target.value;
        const regex = /^[a-zA-Zа-яА-Я0-9\s]*$/;

        if (regex.test(value)) {
            setSearchTerm(value);
            setError('');
            if (value.length >= 2) {
                fetchProducts(value);
            }
        } else {
            setError('Please use only letters from the Latin or Cyrillic alphabet, numbers, and spaces.');
        }
    };

    const handleSearchClick = () => {
        fetchProducts(searchTerm);
    };

    return (
        <div>
            <h1>Product List</h1>
            <input
                type="text"
                placeholder="Search products..."
                value={searchTerm}
                onChange={handleSearchChange}
            />
            <button onClick={handleSearchClick}>Search</button>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        {product.name} - {product.category} - ${product.price} - {product.brand}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default ProductList;
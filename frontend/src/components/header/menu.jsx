import React from 'react';
import './header.css';
import MenuItem from "./menu-item";

const menu_items = [
    {
        image: require('../../assets/dashboard.png'),
        content: 'Dashboard'
    },
    {
        image: require('../../assets/paper.png'),
        content: 'Assignment'
    },
    {
        image: require('../../assets/user.png'),
        content: 'User'
    }
];

const Menu = () => {
    return (
        <nav className="menu">
            {menu_items.map((item, index) => (
                <MenuItem key={index} item={item} />
            ))}
        </nav>
    );
};

export default Menu;
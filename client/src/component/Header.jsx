import React, {Component} from 'react';

class Header extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employees: []
        }
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="" className="navbar-brand">Employee Management</a></div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default Header;
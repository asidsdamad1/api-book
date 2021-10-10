import React, {Component} from 'react';
import EmployeeService from "../services/EmployeeService";
import {Nav, NavItem, NavLink} from "reactstrap";
import Profile from "./User/Profile";

class Header extends Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [],
            isLogin: localStorage.getItem("accessToken") != null
        }
        this.login = this.login.bind(this);

    }

    componentDidMount() {
        EmployeeService.getEmployee().then((res) => {
            this.setState({employees: res.data})
        })
    }

    login() {
        this.props.history.push('/signin');
    }

    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="" className="navbar-brand">Employee Management</a></div>
                        <div className={"navRight"}>
                            <Nav>
                                <NavItem>
                                    <NavLink href="/signin">Login</NavLink>
                                </NavItem>

                                <NavItem>
                                    <NavLink href="/signup">SignUp</NavLink>
                                </NavItem>
                            </Nav>
                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default Header;
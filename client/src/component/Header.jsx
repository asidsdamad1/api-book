import {Nav, NavbarText, NavbarToggler, NavItem, NavLink} from "reactstrap";
import {Collapse} from "react-bootstrap";
import AuthenticationService from "../services/AuthenticationService";
import {Component} from "react";

class Header extends Component {
    constructor(props) {
        super(props);
        this.toggle = this.toggle.bind(this);
        this.login = this.login.bind(this);
        this.signOut = this.signOut.bind(this);

        this.state = {
            isOpen: false,
            showUser: false,
            showPM: false,
            showAdmin: false,
            username: undefined,
            login: false
        };

    }

    componentDidMount() {
        const user = AuthenticationService.getCurrentUser();
        console.log(this.state + "\n");

        if (user) {
            const roles = [];

            user.authorities.forEach(authority => {
                roles.push(authority.authority)
            });

            this.setState({
                showUser: true,
                showPM: roles.includes("ROLE_PM") || roles.includes("ROLE_ADMIN"),
                showAdmin: roles.includes("ROLE_ADMIN"),
                login: true,
                username: user.username
            });

        console.log(this.state);
        }

    }

    login() {
        this.props.history.push('/signin');
    }
    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }
    signOut = () => {
        AuthenticationService.signOut();
        window.location.reload();
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div><a href="/" className="navbar-brand">Employee Management</a></div>
                        <div className={"navRight"}>

                            {this.state.login ? (
                                        <Nav className="ml-auto" navbar>
                                            <NavItem>
                                                <NavLink>
                                                    Hi, <a href="/profile">{this.state.username}</a>
                                                </NavLink>
                                            </NavItem>
                                            <NavItem>
                                                <NavLink href="/" onClick={this.signOut}>SignOut</NavLink>
                                            </NavItem>
                                        </Nav>
                                    ) : (
                                        <Nav className="ml-auto" navbar>
                                            <NavItem>
                                                <NavLink href="/signin">Login</NavLink>
                                            </NavItem>
                                            <NavItem>
                                                <NavLink href="/signup">SignUp</NavLink>
                                            </NavItem>
                                        </Nav>
                                    )
                                }

                        </div>
                    </nav>
                </header>
            </div>
        );
    }
}

export default Header;
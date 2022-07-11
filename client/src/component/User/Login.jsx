import React, {Component} from 'react';
import {
    Alert, Button, Col, Container, Form, FormGroup, Row,
    Label,
    Input
} from "reactstrap";
import {Link} from "react-router-dom";
import avatar from '../../avatar.png';
import AuthenticationService from "../../services/AuthenticationService";
import Header from "../Header";

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: ""
        };
    }

    changeHandler = (event) => {
        this.setState({[event.target.name] : event.target.value})
    }

    doLogin = async (event) => {
        event.preventDefault();

        AuthenticationService
            .signin(this.state.username,
                this.state.password)
            .then(
                () => {
                    this.props.history.push('/');
                    window.location.reload()
                },
                error => {
                    console.log("Login fail: error = { " + error.toString() + " }");
                    this.setState({error: "Can not signin successfully ! Please check username/password again"});
                }

            );
    }

    render() {
        return (
            <div>
                <Container fluid>
                    <Row style={{marginTop: "20px"}}>
                        <Col sm="12" md={{size: 3, offset: 4}}>
                            <div style={{marginBottom: "10px"}}>
                                <img src={avatar} alt="Avatar" className="avatar center"
                                     style={{width: "50%", height: "auto"}}/>
                            </div>
                            <Form onSubmit={this.doLogin}>
                                <FormGroup>
                                    <Label for="username"><strong>Username</strong></Label>
                                    <Input autoFocus
                                           type="text"
                                           name="username" id="username"
                                           value={this.state.username}
                                           placeholder="Enter Username"
                                           autoComplete="username"
                                           onChange={this.changeHandler}
                                    />
                                </FormGroup>

                                <FormGroup>
                                    <Label for="password"><strong>Password</strong></Label>
                                    <Input type="password"
                                           name="password" id="password"
                                           value={this.state.password}
                                           placeholder="Enter Password"
                                           autoComplete="password"
                                           onChange={this.changeHandler}
                                    />
                                </FormGroup>

                                <Button type="submit" variant="primary" size="lg" block >
                                    Sign In
                                </Button>
                                {
                                    this.state.error && (
                                        <Alert color="danger">
                                            {this.state.error}
                                        </Alert>
                                    )
                                }
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </div>);
    }
}

export default Login;
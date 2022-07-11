import React, {Component} from 'react';
import EmployeeService from "../../services/EmployeeService";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {Alert} from "react-bootstrap";
import {Button, Col, Container, Form, FormGroup, Input, Label, Row} from "reactstrap";

toast.configure({
    autoClose: 2000,
    draggable: false,
    limit: 3
});

const validEmailRegex = RegExp(/^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i);
const validPhoneRegex = RegExp(/^([+]?[\s0-9]+)?(\d{3}|[(]?[0-9]+[)])?([-]?[\s]?[0-9])+$/i);
const validateForm = (errors) => {
    let valid = true;
    Object.values(errors).forEach(
        (val) => val.length > 0 && (valid = false)
    );
    return valid;
}
class CreateEmployee extends Component {
    constructor(props) {
        super(props);

        this.state = {

            id: this.props.match.params.id,
            name: '',
            code: '',
            email: '',
            age: '',
            phone: '',
            errors: {
                name: '',
                code: '',
                email: '',
                age: '',
                phone: ''
            }

        }
        this.saveOrUpdate = this.saveOrUpdate.bind(this);

    }
    changeHandler = (event) => {
        const { name, value } = event.target;

        let errors = this.state.errors;

        switch (name) {
            case 'name':
                errors.name =
                    value.length < 3
                        ? 'Name must be 3 characters long!'
                        : '';
                break;
            case 'code':
                errors.code =
                    value.length < 3
                        ? 'LastName must be 3 characters long!'
                        : '';
                break;
            case 'email':
                errors.email =
                    value.length < 5
                        ? 'Username must be 5 characters long!'
                        : '';
                break;
            case 'age':

                break;
            case 'phone':
                errors.phone =
                    validPhoneRegex.test(value)
                        ? ''
                        : 'Phone number is not valid'
                break;
            default:
                break;
        }

        this.setState({errors, [name]: value}, ()=> {
            console.log(errors)
        })
    }

    componentDidMount() {
        if (this.state.id === ' ') {

        } else {
            EmployeeService.getEmployeeById(this.state.id).then((res) => {
                let employee = res.data
                this.setState({
                    code: employee.code,
                    name: employee.name,
                    email: employee.email,
                    age: employee.age,
                    phone: employee.phone
                })
            })
        }

    }

    saveOrUpdate = (e) => {
        e.preventDefault();
        const valid = validateForm(this.state.errors);
        this.setState({ validForm: valid});

        // let {match: {params}} = this.props;
        //
        // let {id} = params;

        // console.log(id)
        let employee = {
            name: this.state.name,
            code: this.state.code,
            email: this.state.email,
            age: this.state.age,
            phone: this.state.phone,
        }
        console.log('employee => ' + JSON.stringify(employee));

        if(valid) {
            EmployeeService.checkCode(this.state.id, employee.code).then((res) => {
                if (res.data) {
                    toast.error('Mã đã được sử dụng!')
                } else {
                    if (this.state.id.trim() === '') {

                        EmployeeService.saveEmployee(employee, this.state.id).then((res) => {
                            this.props.history.push("/employees")
                            toast.success('Thêm thành công!')
                        })
                    } else {
                        EmployeeService.updateEmployee(employee, this.state.id).then((res) => {
                            this.props.history.push("/employees")
                            toast.success('Cập nhật thành công!')
                        })
                    }
                }

            })
        }


    }


    cancel() {
        this.props.history.push('/employees');
    }

    render() {
        const style = {
            color: "red",
            fontSize: 12
        }
        const title = <h2>Register User</h2>;
        const errors = this.state.errors;

        let alert = "";

        if(this.state.message){
            if(this.state.successful){
                alert = (
                    <Alert variant="success">
                        {this.state.message}
                    </Alert>
                );
            }else{
                alert = (
                    <Alert variant="danger">
                        {this.state.message}
                    </Alert>
                );
            }
        }
        return (
            <div>
                <Container fluid>
                    <Row>
                        <Col sm="12" md={{ size: 4, offset: 4 }}>
                            {title}
                            <Form onSubmit={this.signUp}>
                                <FormGroup controlId="formName">
                                    <Label for="name">Name</Label>
                                    <Input
                                        type="text"
                                        placeholder="Enter Name"
                                        name="name" id="name"
                                        value={this.state.name}
                                        autoComplete="name"
                                        onChange={this.changeHandler}

                                    />
                                    <div >
                                        {
                                            errors.name && (
                                                <span style={style}>
                                                    * {errors.name}
                                                </span>
                                            )
                                        }
                                    </div>

                                </FormGroup>

                                <FormGroup controlId="formCode">
                                    <Label for="code">Code</Label>
                                    <Input
                                        type="text"
                                        placeholder="Enter Code"
                                        name="code" id="code"
                                        value={this.state.code}
                                        autoComplete="code"
                                        onChange={this.changeHandler}
                                    />

                                </FormGroup>

                                <FormGroup controlId="formEmail">
                                    <Label for="email">Email</Label>
                                    <Input
                                        type="text"
                                        placeholder="Enter Email"
                                        name="email" id="email"
                                        value={this.state.email}
                                        autoComplete="email"
                                        onChange={this.changeHandler}
                                        style={{zIndex:1}}
                                    />
                                    <div >
                                        {
                                            errors.email && (
                                                <span style={style}>
                                                    * {errors.email}
                                                </span>
                                            )
                                        }
                                    </div>
                                </FormGroup>

                                <FormGroup controlId="formAge">
                                    <Label for="age">Age</Label>
                                    <Input required
                                           type="number"
                                           placeholder="Enter Age"
                                           name="age" id="age"
                                           value={this.state.age}
                                           autoComplete="age"
                                           onChange={this.changeHandler}

                                    />
                                    <div >
                                        {
                                            errors.age && (
                                                <span style={style}>
                                                    * {errors.age}
                                                </span>
                                            )
                                        }
                                    </div>
                                </FormGroup>

                                <FormGroup controlId="formPhone">
                                    <Label for="phone">Phone</Label>
                                    <Input required
                                           type="phone"
                                           placeholder="Enter Phone"
                                           name="phone" id="phone"
                                           value={this.state.phone}
                                           autoComplete="phone"
                                           onChange={this.changeHandler}
                                    />
                                    <div >
                                        {
                                            errors.phone && (
                                                <span style={style}>
                                                    * {errors.phone}
                                                </span>
                                            )
                                        }
                                    </div>
                                </FormGroup>

                                <Button className={"btn btn-success"} onClick={this.saveOrUpdate}
                                       >Save
                                </Button>
                                <Button className="btn btn-danger" onClick={this.cancel.bind(this)}
                                        style={{marginLeft: "10px"}}>Cancel
                                </Button>
                                {
                                    !this.state.validForm && (
                                        <Alert key="validForm" variant="danger">
                                            Please check the inputs again!
                                        </Alert>
                                    )
                                }
                                {alert}
                            </Form>
                        </Col>
                    </Row>
                </Container>
            </div>);
    }
}

export default CreateEmployee;
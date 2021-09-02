import React, {Component} from 'react';
import EmployeeService from "../services/EmployeeService";
import {toast} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {useParams} from "react-router-dom";
toast.configure({
    autoClose: 2000,
    draggable: false,
    limit: 3
    //etc you get the idea
});

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
            value: '',
            isInputValid: true,
            errorMessage: ''

        }
        this.changeCodeHandler = this.changeCodeHandler.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneHandler = this.changePhoneHandler.bind(this);
        this.changeAgeHandler = this.changeAgeHandler.bind(this);
        this.saveOrUpdate = this.saveOrUpdate.bind(this);
        const validateInput = (checkingText) => {
            /* reg exp để kiểm tra xem chuỗi có chỉ bao gồm 10 - 11 chữ số hay không */

            const regexp = /^\d{10,11}$/;
            const checkingResult = regexp.exec(checkingText);
            if (checkingResult !== null) {
                return { isInputValid: true,
                    errorMessage: ''};
            } else {
                return { isInputValid: false,
                    errorMessage: 'Số điện thoại phải có 10 - 11 chữ số.'};
            }
        }
    }
    handleInput = event => {
        const { value } = event.target;
        this.setState({value});
    }
    /* Tạo method handleInputValidation để kiểm tra nội dung input, rồi cập nhật trạng thái vào state*/

    handleInputValidation = event => {
        const { isInputValid, errorMessage } = validateInput(this.state.value);
        this.setState({
            isInputValid: isInputValid,
            errorMessage: errorMessage
        })
    }
    componentDidMount() {
        if(this.state.id === ' ') {
            return
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

        EmployeeService.checkCode(this.state.id, employee.code).then((res) => {
            if(res.data) {
                toast.error('Mã đã được sử dụng!')
            } else {
                if ( this.state.id === ' ') {
                        EmployeeService.saveEmployee(employee).then((res) => {
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
    changeNameHandler = (event) => {
        this.setState({name: event.target.value});
    }
    changeCodeHandler = (event) => {
        this.setState({code: event.target.value});
    }
    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
    }
    changePhoneHandler = (event) => {
        this.setState({phone: event.target.value});
    }
    changeAgeHandler = (event) => {
        this.setState({age: event.target.value});
    }

    cancel() {
        this.props.history.push('/employees');
    }

    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Employee</h3>
                            <div className="card-body">
                                <form action="">
                                    <div className="form-group" >
                                        <label>Name: </label>
                                        <input placeholder={"Name"} name={"name"} className={"form-control"}
                                               value={this.state.name} onChange={this.changeNameHandler} required={true}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Code: </label>
                                        <input placeholder={"Code"} name={"code"} className={"form-control"}
                                               value={this.state.code} onChange={this.changeCodeHandler} required={true}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Email: </label>
                                        <input type={"email"} placeholder={"Email"} name={"email"} className={"form-control"}
                                               value={this.state.email} onChange={this.changeEmailHandler} required={true}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Age: </label>
                                        <input placeholder={"Age"} name={"age"} className={"form-control"}
                                               value={this.state.age} onChange={this.changeAgeHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone: </label>
                                        <input placeholder={"Phone"} name={"phone"} className={"form-control"}
                                               value={this.state.phone} onChange={this.changePhoneHandler}/>
                                    </div>

                                    <button className={"btn btn-success"} onClick={this.saveOrUpdate} style={{marginLeft: "10px"}}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

export default CreateEmployee;
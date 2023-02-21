import React from "react";
import {Formik, useFormik} from "formik";
import * as Yup from 'yup';
import {Button, Input, Tag} from "antd";
import {createStudent} from "../client/StudentClient";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;
import {errorNotification, successNotification} from "../common/Notification";

export const AddStudentForm = ({addStudent,onSuccess}) => {
    const marginStyle = {marginBottom:'15px'}
    const tagStyle = {backgroundColor:'#f51',color:'white',...marginStyle}
    const formik = useFormik({
        initialValues: {
            firstName: '',
            lastName: '',
            email: '',
            gender:''
        },
        validationSchema: Yup.object({
            firstName: Yup.string()
                .max(15, 'Must be 15 characters or less')
                .required('First name required'),
            lastName: Yup.string()
                .max(20, 'Must be 20 characters or less')
                .required('Last name required'),
            email: Yup.string().email('Invalid email address').required('Email required'),
            gender: Yup.string().required('Required').oneOf(['male','female','MALE','FEMALE'],'Invalid gender')
        }),
        onSubmit: values => {
            createStudent(values)
                .then(_ => {
                addStudent(values)
                onSuccess()
                    successNotification("Success","You are successfully added student")
            })
                .catch(error => {
                    console.log(error)
                    const message = "Error"
                    const description = error.response.data.message
                    errorNotification(message,description)
                });
        },
    });
    return (
        <form onSubmit={formik.handleSubmit}>
            <label htmlFor="firstName">First Name</label>
            <Input
                style={marginStyle}
                id="firstName"
                type="text"
                placeholder="last name"
                {...formik.getFieldProps('firstName')}
            />
            {formik.touched.firstName && formik.errors.firstName ? (
                <Tag style={tagStyle}>{formik.errors.firstName}</Tag>
            ) : null}
            <div>
            <label style={marginStyle} htmlFor="lastName">Last Name</label>
            <Input id="lastName"
                   style={marginStyle}
                   type="text" {...formik.getFieldProps('lastName')}
                   placeholder="last name"
            />
            {formik.touched.lastName && formik.errors.lastName ? (
                <Tag style={tagStyle}>{formik.errors.lastName}</Tag>
            ) : null}
            </div>
            <label htmlFor="email">Email Address</label>
            <Input id="email" type="email"
                   style={marginStyle}
                   placeholder="email"
                   {...formik.getFieldProps('email')} />
            {formik.touched.email && formik.errors.email ? (
                <Tag style={tagStyle}>{formik.errors.email}</Tag>
            ) : null}
            <div>
            <label htmlFor="gender">Gender</label>
            <Input id="gender"
                   style={marginStyle}
                   {...formik.getFieldProps('gender')}
                   placeholder="gender"/>
            {formik.touched.gender && formik.errors.gender ? (
                <Tag style={tagStyle}>{formik.errors.gender}</Tag>
            ) : null}
            </div>

            <Button type="primary" htmlType="submit" style={marginStyle} disabled={!formik.isValid}>Submit</Button>
        </form>
    );
}
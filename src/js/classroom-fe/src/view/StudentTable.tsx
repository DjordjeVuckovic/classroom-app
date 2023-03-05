import React, {useEffect, useState} from "react";
import {getAllStudents} from "../client/StudentClient";
import {IStudent} from "../student/Student";
import {Table, Spin, Modal, Empty} from "antd";
import {Container} from "../common/Container";
import {Footer} from "../components/Footer";
import {AddStudentForm} from "../components/AddStudentForm";
import {errorNotification} from "../common/Notification";

export const StudentTable = () => {
    const [students,setStudents] = useState<IStudent[]>([])
    const [loading,setLoading] = useState<boolean>( true)
    const [isModalOpen,setIsModalOpen] = useState<boolean>( false)
    const columns = [
        {
            title: 'First name',
            dataIndex: 'firstName',
            key: 'firstName',
        },
        {
            title: 'Last name',
            dataIndex: 'lastName',
            key: 'lastName',
        },
        {
            title: 'Email',
            dataIndex: 'email',
            key: 'email',
        },
        {
            title: 'Gender',
            dataIndex: 'gender',
            key: 'gender',
        },
    ];
    useEffect(()=>{
        getAllStudents()
            .then(
            value => {
                setStudents(value.data)
                setLoading(false)
            })
            .catch(error => {
                setLoading(true)
                const message = error.message
                const description = error.response.data.message
                errorNotification(message,description)
                console.log(error)
            })
        }
    ,[])
    function openAddStudentModal() {
        setIsModalOpen(true)
    }
    function closeAddStudentModal() {
        setIsModalOpen(false)
    }

    function addStudent(student) {
        setStudents([...students,student])
    }
    const createStudentElement = () => {
        return <div>
            <Modal
                title='Add new student'
                open={isModalOpen}
                onOk={closeAddStudentModal}
                onCancel={closeAddStudentModal}
                width={800}
                >
                <AddStudentForm addStudent={addStudent} onSuccess={()=> closeAddStudentModal()}/>
            </Modal>
            <Footer numberOfStudent={students.length} onClick={openAddStudentModal}/>
        </div>
    }
    if(loading){
        return (
            <Spin style={{marginTop:"20px"}}/>
        )
    }
    if(students.length === 0){
        return (
            <Container>
                <Empty description={<h1 style={{color:"white"}}>No student found</h1>}/>
                {createStudentElement()}
            </Container>
        )
    }
    return (
        <Container>
            <Table
                style={{backgroundColor:"rgb(0,21,41)",color:"white"}}
                dataSource={students}
                columns={columns}
                rowKey='studentId'
                loading={loading}
                bordered={true}
            />
            {createStudentElement()}
        </Container>
    );
}
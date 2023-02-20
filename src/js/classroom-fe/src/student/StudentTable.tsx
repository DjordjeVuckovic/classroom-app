import React, {useEffect, useState} from "react";
import {getAllStudents} from "../client/StudentClient";
import {IStudent} from "./Student";
import {Table, Spin, Modal} from "antd";
import {Container} from "../common/Container";
import {Footer} from "./Footer";
import {AddStudentForm} from "./AddStudentForm";

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
        getAllStudents().then(data=>{
            setStudents(data)
            setLoading(false)
        }
        )
    },[])
    function openAddStudentModal() {
        setIsModalOpen(true)
    }
    function closeAddStudentModal() {
        setIsModalOpen(false)
    }
    return (
        <Container>
            {loading ?
                <Spin/> :
                <div>
                    <Table
                        style={{backgroundColor:"#23395d",color:"white"}}
                        dataSource={students}
                        columns={columns}
                        rowKey='studentId'
                        loading={loading}
                        bordered={true}
                    />
                    <Modal
                        title='Add new student'
                        open={isModalOpen}
                        onOk={closeAddStudentModal}
                        onCancel={closeAddStudentModal}
                        width={1000}
                        >
                        <AddStudentForm/>
                    </Modal>
                    <Footer numberOfStudent={students.length} onClick={openAddStudentModal}/>
                </div>

            }
        </Container>

    );
}
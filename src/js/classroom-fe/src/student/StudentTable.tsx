import React, {useEffect, useState} from "react";
import {getAllStudents} from "../client/StudentClient";
import {IStudent} from "./Student";
import {Table,Avatar} from "antd";
import {Container} from "../common/Container";

export const StudentTable = () => {
    const [students,setStudents] = useState<IStudent[]>([])
    const [loading,setLoading] = useState<boolean>( true)
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
            setLoading(!loading)
        }
        )
    },[])
    return (
        <Container>
        <Table
            dataSource={students}
                  columns={columns}
                  rowKey='studentId'
            loading={loading}
            bordered={true}
        />
        </Container>

    );
}
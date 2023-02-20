import React from "react";
import {Container} from "../common/Container";
import {Avatar, Button} from "antd";
import "./footer.css"

export const Footer = ({numberOfStudent,onClick}) => {
    return (
        <div className="footer">
            <Container>
                <Avatar style={{backgroundColor:'#f56a00',marginRight: '10px'}}
                size='large'>{numberOfStudent}</Avatar>
                <Button type="primary" onClick={onClick} className="button-prim">Add new student +</Button>
            </Container>
        </div>
    );
}
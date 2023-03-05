import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Layout, Menu } from 'antd';
import {
    UserOutlined,
    BookOutlined,
    HomeOutlined
} from '@ant-design/icons';
import { useSpring, animated } from 'react-spring';

const { Sider } = Layout;

export const SideBar = () => {
    const [collapsed, setCollapsed] = useState(false);

    const sidebarProps = useSpring({
        width: collapsed ? 120 : 250,
    });

    const toggleSidebar = () => {
        setCollapsed(!collapsed);
    };

    return (
        <animated.div style={sidebarProps}>
            <Sider
                style={{ position: 'fixed', height: '100vh', backgroundColor:"rgb(0,21,41)",padding:"10px"}}
                collapsible
                collapsed={collapsed}
                onCollapse={toggleSidebar}
                theme="dark"
            >
                <div style={{ textAlign: 'center', margin: 5 }}>
                    <img src="src/assets/online-course.png" alt="Logo" width={54} />
                    <h2 style={{ display: collapsed ? 'none' : 'inline-block', margin: 10,fontSize:"22px"}}>
                        Classy
                    </h2>
                </div>
                <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                    <Menu.Item key="1" icon={<HomeOutlined />}>
                        <Link to="/">Home</Link>
                    </Menu.Item>
                    <Menu.Item key="2" icon={<UserOutlined />}>
                        <Link to="/students">Students</Link>
                    </Menu.Item>
                    <Menu.Item key="3" icon={<BookOutlined />}>
                        <Link to="/courses">Courses</Link>
                    </Menu.Item>
                </Menu>
            </Sider>
        </animated.div>
    );
};

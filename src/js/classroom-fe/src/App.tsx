import './App.css'
import React from 'react'
import {StudentTable} from "./view/StudentTable";
import {Route, Routes} from "react-router-dom";
import {Courses} from "./view/Courses";
import {Home} from "./view/Home";
import {SideBar} from "./components/SideBar";

function App() {
  return (
    <div className="App container">
        <div className="sidebar">
        <SideBar/>
        </div>
        <div className="main-content">
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/students" element={<StudentTable/>}/>
                <Route path="/courses" element={<Courses />}/>
            </Routes>
        </div>
    </div>
  )
}

export default App

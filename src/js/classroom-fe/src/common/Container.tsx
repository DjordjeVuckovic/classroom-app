import React from "react";

export const Container = ({children}) => {
    return (
        <div style={{margin: '0 auto',width:"1000px"}}>
            {children}
        </div>
    );
}
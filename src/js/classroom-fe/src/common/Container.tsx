import React from "react";

export const Container = ({children}) => {
    return (
        <div style={{width: '1400px', margin: '0 auto'}}>
            {children}
        </div>
    );
}
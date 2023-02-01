import {ApiProvider} from "@reduxjs/toolkit/dist/query/react";
import Container from "react-bootstrap/Container";
import TodoItemList from "./components/TodoItemList";
import {api} from "./store/api";

export const App = () => (
    <ApiProvider api={api}>
        <Container fluid="sm" className="mw-md">
            <h1>My Todos</h1>
            <TodoItemList/>
        </Container>
    </ApiProvider>
)

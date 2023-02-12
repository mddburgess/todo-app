import {render} from "@testing-library/react";
import {TodoItem} from "../../src/api/todoApi";
import TodoItemRow from "../../src/components/TodoItemRow";

describe("TodoItemRow", () => {

    const todoItem: TodoItem = {
        id: null,
        summary: "Summary",
        created_at: null,
        updated_at: null,
    }

    it("displays the summary", () => {
        const result = render(<TodoItemRow item={todoItem}/>)
        expect(result.getByText("Summary")).toBeVisible()
    })
})

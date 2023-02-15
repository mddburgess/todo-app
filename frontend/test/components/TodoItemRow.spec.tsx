import {render} from "@testing-library/react";
import {TodoItem} from "../../src/api/todoApi";
import TodoItemRow from "../../src/components/TodoItemRow";
import userEvent from "@testing-library/user-event";

describe("TodoItemRow", () => {

    const todoItem: TodoItem = {
        id: 1,
        summary: "Summary",
        created_at: null,
        updated_at: null,
    }

    const onDelete = jest.fn()

    it("displays the summary", () => {
        const result = render(<TodoItemRow item={todoItem} onDelete={onDelete}/>)
        expect(result.getByText("Summary")).toBeVisible()
    })

    it("displays a button to delete the todo item", () => {
        const result = render(<TodoItemRow item={todoItem} onDelete={onDelete}/>)
        expect(result.getByRole("button")).toBeVisible()
    })

    it("fires the delete action when the delete button is clicked", async () => {
        const user = userEvent.setup()
        const result = render(<TodoItemRow item={todoItem} onDelete={onDelete}/>)
        const deleteButton = result.getByRole("button")

        await user.click(deleteButton)
        expect(onDelete).toBeCalledWith(todoItem.id)
    })
})

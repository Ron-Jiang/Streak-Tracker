import { useState } from "react"
import { createHabit } from "./Services";

function AddHabit({ onAdd, onSelect }) {
    const [habit, setHabit] = useState({
        habitName: '',
        habitDescription: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setHabit((prev) => ({
            ...prev,
            [name]: value
        }))
    }

    const handleSubmit = async (e) => {
        try {
            const { data } = await createHabit(habit);
            onAdd(data);
            alert('Success!');
            onSelect(data.id);
        } catch (e) {
            console.log(e);
            alert('Failed to create habit');
        }
    }

    return (
        <div className="flex flex-col gap-6 p-9 max-w-md">
            <div>
                <p className="text-zinc-500 text-xs tracking-widest mb-1">Start Here</p>
                <h1 className="text-white font-bold tracking-wider text-4xl">Add a New Habit</h1>
            </div>
            <div>
                <div>
                    <label className="block text-zinc-500 text-xs tracking-widest mb-2">Name</label>
                    <input
                        type="text"
                        name="habitName"
                        value={habit.habitName}
                        onChange={handleChange}
                        placeholder="Habit Name"
                        className='bg-zinc-900 border border-zinc-700 text-zinc-200 text-sm px-4 py-3 rounded-lg outlie-none focus:border-amber-500 transition-colors placeholder-zinc-600 w-full'
                    />
                </div>
                <div>
                    <label className="block text-zinc-500 text-xs tracking-widest mb-2 mt-2">Description</label>
                    <input
                        type="text"
                        name="habitDescription"
                        value={habit.habitDescription}
                        onChange={handleChange}
                        placeholder="description"
                        className='bg-zinc-900 border border-zinc-700 text-zinc-200 text-sm px-4 py-3 rounded-lg outlie-none focus:border-amber-500 transition-colors placeholder-zinc-600 w-full'
                    />
                </div>
            </div>
            <button onClick={handleSubmit} className="bg-amber-500 text-black font-bold text-sm tracking-wider px-6 py-3 rounded-lg w-fit hover:opacity-80 transition-opacity cursor-pointer">Create</button>
        </div >
    )
}

export default AddHabit
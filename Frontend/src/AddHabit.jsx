import { useState } from "react"
import { createHabit } from "./Services";

function AddHabit({onAdd, onSelect}) {
    const [habit, setHabit] = useState({
        habitName: '',
        habitDescription: ''
    });

    const handleChange = (e) => {
        const {name, value} = e.target;
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
        } catch(e) {
            console.log(e);
            alert('Failed to create habit');
        }
    }

    return (
        <>
            <div className="text-amber-700">
                <div className="flex flex-col">
                    
                    <input 
                        type="text" 
                        name="habitName"
                        value={habit.habitName}
                        onChange={handleChange}
                        placeholder="name"
                        className='border border-r-amber-400 rounded-full'
                    />
                    <input 
                        type="text" 
                        name="habitDescription"
                        value={habit.habitDescription}
                        onChange={handleChange}
                        placeholder="description"
                        className='border border-r-amber-400 rounded-full'
                    />
                    <button onClick={handleSubmit}>SUBMIT</button>
                </div>
            </div>
        </>
    )
}

export default AddHabit
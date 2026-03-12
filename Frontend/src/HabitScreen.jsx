import { completeHabitToday, deleteHabit } from "./Services";

function HabitScreen({selectedHabit, onUpdate, onDelete}) {
    
    const isDone = () => {
        if (!selectedHabit.lastCompleted) {
            return false;
        }
        const toPST = (date) => new Date(date).toLocaleDateString('en-CA', { timeZone: 'America/Los_Angeles' });
        const today = toPST(new Date().toISOString().split('T')[0]);
        return selectedHabit.lastCompleted === today;
    }

    const handleIncrement = async () => {
        try {
            const {data} = await completeHabitToday(selectedHabit.id);
            onUpdate(data);
        } catch (e) {
            console.log(e);
            alert('Something went wrong with incrementing.');
        }
    }

    const handleDelete = async () => {
        try {
            const confirmChoice = confirm('Are you sure you want to delete?');
            if (!confirmChoice) {
                return;
            }
            await deleteHabit(selectedHabit.id);
            onDelete(selectedHabit.id);
        } catch(e) {
            console.log(e);
            alert('Something went wrong with deletion.');
        }
    }

    const status = isDone();
    
    return (
        <>
            <div className="ml-20 flex flex-col items-center">
                <p className="text-amber-50">{selectedHabit.habitName}: {selectedHabit.habitDescription}</p>
                {status? (<p className="text-green-400">All done today.</p>) : (<button onClick={handleIncrement} className="text-yellow-500 cursor-pointer">Click here to mark your streak as done for today</button>)}
                

                <button onClick={handleDelete} className="text-red-500 cursor-pointer">Click here to delete this streak from your folder</button>

            </div>
        </>
    )
}

export default HabitScreen
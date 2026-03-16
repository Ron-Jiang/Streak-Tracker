import { completeHabitToday, deleteHabit } from "./Services";

function HabitScreen({selectedHabit, onUpdate, onDelete}) {
    
    const isDone = () => {
        if (!selectedHabit.lastCompleted) {
            return false;
        }
        const today = new Date().toLocaleDateString('en-CA');
        const todayTimeFields = today.split('-');
        const lastCompletedTimeFields = selectedHabit.lastCompleted.split('-');
        if ((todayTimeFields[0] === lastCompletedTimeFields[0]) && (todayTimeFields[1] === lastCompletedTimeFields[1]) && (todayTimeFields[2] === lastCompletedTimeFields[2])) {
            return true;
        } else {
            return false;
        }
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
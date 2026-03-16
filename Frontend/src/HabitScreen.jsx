import { completeHabitToday, deleteHabit } from "./Services";

function HabitScreen({ selectedHabit, onUpdate, onDelete }) {

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
            const { data } = await completeHabitToday(selectedHabit.id);
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
        } catch (e) {
            console.log(e);
            alert('Something went wrong with deletion.');
        }
    }

    const status = isDone();

    return (
        <div className="flex flex-col gap-6 p-8 max-w-lg">
            <div>
                <p className="text-zinc-500 text-xs tracking-widdest mb-1">HABIT</p>
                <h1 className="text-white font-bold tracking-wider text-4xl">{selectedHabit.habitName}</h1>
                <p className="text-zinc-500 text-sm mt-1">{selectedHabit.habitDescription || 'No Description'}</p>
            </div>

            <div className="flex gap-4">
                <div className="bg-zinc-900 border border-zinc-700 rounded-lg px-5 py-4">
                    <p className="text-amber-500 font-bold text-4xl tracking-wider leading-none">{selectedHabit.currentStreak}</p>
                    <p className="text-zinc-500 text-xs tracking-widest mt-2">Current Streak</p>
                </div>
                <div className="bg-zinc-900 border border-zinc-700 rounded-lg px-5 py-4">
                    <p className="text-white font-bold text-4xl tracking-wider leading-none">{selectedHabit.longestStreak}</p>
                    <p className="text-zinc-500 text-xs tracking-widest mt-2">Longest Streak</p>
                </div>
            </div>

            {status ? 
            <div className="bg-green-950 border border-green-900 rounded-lg px-5 py-3 text-green-400 text0xs tracking-wider w-fit">ALL DONE TODAY!</div> : (
            <button onClick={handleIncrement} className="bg-amber-500 text-black font-bold text-sm tracking-wider rounded-lg px-6 py-3 w-fit hover:opacity-75 transition-opacity cursor-pointer">Mark as Done!</button>)}


            <button onClick={handleDelete} className="text-zinc-600 text-s tracking-wider w-fit mt-4 hover:text-red-500 transition-colors cursor-pointer">Delete Habit</button>
        </div>
    )
}

export default HabitScreen
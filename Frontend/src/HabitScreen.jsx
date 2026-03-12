import { completeHabitToday, deleteHabit } from "./Services";

function HabitScreen({selectedHabit, onUpdate, onDelete}) {
    
    const isDone = () => {
        if (!selectedHabit.lastCompleted) {
            return false;
        }
        // default format: 2026-03-12
        const today = new Date().toLocaleDateString('en-CA'); // should be system time, en-CA 0 pads
        // console.log(`TODAY: ${today}`);
        // console.log('===== Debugging starts here =====');
        // console.log(`Here is the last completed date stored: ${selectedHabit.lastCompleted}. This should already be in local timezone`);
        // console.log(`> Here is the time we try to get for TODAY. ${today}`);
        // const time = new Date().toLocaleTimeString();
        // console.log(`>> Time?: ${time}`);
        // console.log('===== START =====');
        const todayTimeFields = today.split('-');
        // console.log(todayTimeFields);
        // console.log(todayTimeFields[0]); //yr
        // console.log(todayTimeFields[1]); // month
        // console.log(todayTimeFields[2]); // day
        // console.log(' --- ');
        const lastCompletedTimeFields = selectedHabit.lastCompleted.split('-');
        // console.log(lastCompletedTimeFields[0]); // yr 
        // console.log(lastCompletedTimeFields[1]); // month
        // console.log(lastCompletedTimeFields[2]); // day
        // console.log(' --- results ---');
        if ((todayTimeFields[0] === lastCompletedTimeFields[0]) && (todayTimeFields[1] === lastCompletedTimeFields[1]) && (todayTimeFields[2] === lastCompletedTimeFields[2])) {
            return true;
        } else {
            return false;
        }
        // return selectedHabit.lastCompleted === today;
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
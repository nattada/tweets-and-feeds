import TweeterContent from "./component/media/TwitterContent";
import FeedContent from "./component/media/FeedContent";
import classes from "./App.module.css";

function App() {
  return (
    <div>
        <TweeterContent />
      <div className={classes.feed}>
        <FeedContent />
      </div>
    </div>
  );
}

export default App;

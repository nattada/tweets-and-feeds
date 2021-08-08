import TweeterContent from "./component/media/TwitterContent";
import FeedContent from "./component/media/FeedContent";
import classes from "./App.module.css";

function App() {
  return (
    <div className={classes.container}>
      <div className={classes.container__half}>
        <TweeterContent />
      </div>
      <div className={classes.container__half}>
        <FeedContent />
      </div>
    </div>
  );
}

export default App;

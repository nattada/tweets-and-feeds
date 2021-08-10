import Card from "../ui/Card";
import classes from "./TweetItem.module.css"
function TweetItem(props) {
  return (
    <Card >
      <div className={classes.tweetBox}>
      <div className={classes.tweetHeader}> 
        <div lcassName={classes.tweetImage}>
          <img src={props.image} alt={props.displayName} />
        </div>
        <div className={classes.tweetName}>{props.displayName}</div>
      </div>
      <div>{props.postedOn}</div>
      <div>{props.content}</div>
      </div>
    </Card>
  );
}
export default TweetItem;

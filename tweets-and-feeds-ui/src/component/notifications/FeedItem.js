import classes from "../ui/Timeline.module.css";

function FeedItem(props) {
  return (
    <li className={classes.container__item}>
      <div className={classes.container__top}>
        <div className={classes.avatar}>
        <div className={classes.avatar__letters}>NN</div>
        </div>
        <div className={classes.container__title}>{props.postedBy}</div>
      </div>
      <div className={classes.container__desc}>{props.content}t </div>
    </li>
  );
}
export default FeedItem;

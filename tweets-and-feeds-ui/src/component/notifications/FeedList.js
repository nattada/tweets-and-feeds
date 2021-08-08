import FeedItem from "./FeedItem"
import classes from "../ui/Timeline.module.css";
import Card from '../ui/Card';



function FeedList(props) {
  return (
    <>
      {props.allFeeds.map((feed) => (
        <FeedItem
          key={feed.id}
          content={feed.content}
          postedBy={feed.postedBy}
          postedOn={feed.postedOn}
        />
      ))}
    </>
  );
}
export default FeedList;

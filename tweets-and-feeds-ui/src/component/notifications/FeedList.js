import FeedItem from "./FeedItem"

function FeedList(props) {
  return (
    <div>
      {props.allFeeds.map((feed) => (
        <FeedItem
          key={feed.id}
          content={feed.content}
          posterName={feed.posterName}
          postedTime={feed.postedTime}
        />
      ))}
    </div>
  );
}
export default FeedList;

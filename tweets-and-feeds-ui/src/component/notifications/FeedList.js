import FeedItem from "./FeedItem"

function FeedList(props) {
  return (
    <div>
      {props.allFeeds.map((feed) => (
        <FeedItem
          key={feed.id}
          content={feed.content}
          postedBy={feed.postedBy}
          postedOn={feed.postedOn}
        />
      ))}
    </div>
  );
}
export default FeedList;

function TweetItem(props) {
  return (
    <div>
      <div>
        <img src={props.image} alt={props.displayName} />
      </div>
      <div>{props.displayName}
      </div>
      <div>{props.content}
      </div>
    </div>
  );
}
export default TweetItem;

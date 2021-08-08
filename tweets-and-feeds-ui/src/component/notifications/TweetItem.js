import Card from '../ui/Card';

function TweetItem(props) {
  return (
    <Card>
      <div>
        <img src={props.image} alt={props.displayName} />
      </div>
      <div>{props.displayName}
      </div>
      <div>{props.postedOn}</div>
      <div>{props.content}
      </div>
    </Card>
  );
}
export default TweetItem;

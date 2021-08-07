import TweetItem from "./TweetItem"

function TweetList(props){
    return (<div>
         {props.allTweets.map((tweet) => (
        <TweetItem
          key={tweet.id}
          id={tweet.id}
          image={tweet.image}
          content={tweet.content}
          displayName={tweet.displayName}
          postedTime={tweet.postedTime}
        />
      ))}
        </div>
    );
}
export default TweetList;
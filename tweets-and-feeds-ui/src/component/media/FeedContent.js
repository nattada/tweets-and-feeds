import { useState, useEffect } from "react";
import FeedList from "../notifications/FeedList";
import NewFeed from "../NewFeed"

function FeedContent() {
  const [isLoading, setIsLoading] = useState(true);
  const [loadedFeeds, setLoadedFeeds] = useState([]);

  useEffect(() => {
    setIsLoading(true);
    fetch("/feed", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        const Feeds = [];
        for (const key in data) {
          const item = {
            id: key,
            ...data[key],
          };
          Feeds.push(item);
        }
        setIsLoading(false);
        setLoadedFeeds(Feeds);
      });
  }, []);

  if (isLoading) {
    return (
      <section>
        <p>Loading...</p>
      </section>
    );
  }

  return (
    <div>
      <FeedList allFeeds={loadedFeeds} />
      <NewFeed/>
      </div>
  );
}

export default FeedContent;
